package ua.kpi.cad.iadms.engine;

import ua.kpi.cad.iadms.entity.*;

import java.util.*;

public class ExpertCompetenceEngineImpl implements ExpertCompetenceEngine {

    public List<ExpertCompetence> countExpertCompetence(List<Expert> experts, int quantityOfQuestions) {
        List<List<List<Double>>> matrices = new ArrayList<>(quantityOfQuestions);

        initializeMatrices(matrices, quantityOfQuestions, experts.size() - 1);

        fillUpMatrices(experts, matrices);

        List<Double> minimums = findMinimumsInMatrices(matrices);

        goTo3AngelsMatrices(matrices, minimums);

        List<List<Double>> resultMatrix = buildResultMatrix(matrices, experts.size() - 1);

        normalizeResultMatrix(resultMatrix);

        List<List<Double>> normalizedMatrix = expandMatrixWithZeros(resultMatrix);

        List<ExpertCompetence> expertCompetences = countExpertCompetence(normalizedMatrix, experts);

        normalizeExpertCompetences(expertCompetences);

        return expertCompetences;
    }

    private void initializeMatrices(List<List<List<Double>>> matrices, int quantityOfQuestions, int size) {
        for (int i = 0; i < quantityOfQuestions; i++) {
            matrices.add(new ArrayList<>());
        }

        for (List<List<Double>> matrix : matrices) {
            for (int j = 0; j < size; j++) {
                matrix.add(new ArrayList<>());
            }
        }
    }

    private void fillUpMatrices(List<Expert> experts, List<List<List<Double>>> matrices) {
        for (int i = 0; i < experts.size() - 1; i++) {
            List<ExpertAnswer> answers1 = experts.get(i).getAnswer();
            for (int j = i + 1; j < experts.size(); j++) {
                List<ExpertAnswer> answers2 = experts.get(j).getAnswer();

                for (int k = 0; k < answers1.size(); k++) {
                    List<Variant> variants1 = answers1.get(k).getChosenVariants();
                    List<Variant> variants2 = answers2.get(k).getChosenVariants();

                    double resultValue = 0.0;
                    for (Variant variant1 : variants1) {
                        for (Variant variant2 : variants2) {
                            if (variant1.getIndex() == variant2.getIndex()) {
                                resultValue += variant1.getValue();
                            }
                        }
                    }
                    matrices.get(k).get(i).add(resultValue);
                }
            }
        }
    }

    private List<Double> findMinimumsInMatrices(List<List<List<Double>>> matrices) {
        List<Double> minimums = new ArrayList<>(matrices.size());
        for (List<List<Double>> matrix : matrices) {
            double min = Double.MAX_VALUE;
            for (List<Double> row : matrix) {
                Double d = Collections.min(row);
                min = (d == 0.0) ? min : Math.min(min, d);
            }
            minimums.add(min);
        }

        return minimums;
    }

    private void goTo3AngelsMatrices(List<List<List<Double>>> matrices, List<Double> minimums) {
        int index = 0;
        for (List<List<Double>> matrix : matrices) {
            for (List<Double> row : matrix) {
                for (int j = 0; j < row.size(); j++) {
                    Double d = row.get(j);
                    if (d != 0.0) {
                        d = Math.pow(d, -1);
                    } else {
                        d = Math.pow(minimums.get(index) / 2, -1);
                    }
                    row.set(j, d);
                }
            }
            index++;
        }
    }

    private List<List<Double>> buildResultMatrix(List<List<List<Double>>> matrices, int size) {
        List<List<Double>> resultMatrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            resultMatrix.add(new ArrayList<>());
        }

        for (List<Double> aResultMatrix : resultMatrix) {
            for (int j = 0; j < size; j++) {
                aResultMatrix.add(0.0);
            }
        }

        for (int i = 0; i < matrices.size() - 1; i++) {
            List<List<Double>> matrix1 = matrices.get(i);
            List<List<Double>> matrix2 = matrices.get(i + 1);

            for (int k = 0; k < matrix1.size(); k++) {
                if (i == 0) {
                    resultMatrix.set(k, matrix1.get(k));
                }
                List<Double> res = resultMatrix.get(k);
                List<Double> row2 = matrix2.get(k);

                for (int m = 0; m < row2.size(); m++) {
                    res.set(m, res.get(m) + row2.get(m));
                }
            }
        }

        return resultMatrix;
    }

    private void normalizeResultMatrix(List<List<Double>> resultMatrix) {
        double sum = 0.0;
        for (List<Double> aResultMatrix1 : resultMatrix) {
            for (Double anAResultMatrix1 : aResultMatrix1) {
                sum += anAResultMatrix1;
            }
        }

        for (List<Double> aResultMatrix : resultMatrix) {
            for (int j = 0; j < aResultMatrix.size(); j++) {
                aResultMatrix.set(j, aResultMatrix.get(j) / sum);
            }
        }
    }

    private List<List<Double>> expandMatrixWithZeros(List<List<Double>> matrix) {
        for (List<Double> row : matrix) {
            while (row.size() != matrix.size()) {
                row.add(0, 0.0);
            }
        }

        return matrix;
    }

    private List<ExpertCompetence> countExpertCompetence(List<List<Double>> normalizedMatrix, List<Expert> experts) {
        List<ExpertCompetence> expertCompetences = new ArrayList<>(experts.size());
        for (int i = 0; i < experts.size(); i++) {
            ExpertCompetence expertCompetence = new ExpertCompetence();
            double value = 0.0;
            for (int m = 0; m < normalizedMatrix.size(); m++) {
                for (int n = 0; n < normalizedMatrix.get(m).size(); n++) {
                    if ((n + 1) == i || m == i) {
                        value += normalizedMatrix.get(m).get(n);
                    }
                }
            }
            expertCompetence.setExpert(experts.get(i));
            expertCompetence.setCompetence(value);
            expertCompetences.add(expertCompetence);
        }

        return expertCompetences;
    }

    private void normalizeExpertCompetences(List<ExpertCompetence> expertCompetences) {
        double sumOfCompetence = 0.0;
        for (ExpertCompetence expertCompetence : expertCompetences) {
            sumOfCompetence += expertCompetence.getCompetence();
        }

        for (ExpertCompetence expertCompetence : expertCompetences) {
            expertCompetence.setCompetence(expertCompetence.getCompetence() / sumOfCompetence);
        }
    }

    public static void main(String[] args) {

        Question question1 = new Question(1, "Какой цвет наиболее раздражает зрительные рецепторы ?");
        Variant q1Answer1 = new Variant(1, 1, "Красный", 0.2);
        Variant q1Answer2 = new Variant(2, 2, "Черный", 0.1);
        Variant q1Answer3 = new Variant(3, 3, "Зеленый", 0.5);
        Variant q1Answer4 = new Variant(3, 4, "Синий", 0.2);
        question1.setVariants(Arrays.asList(q1Answer1, q1Answer2, q1Answer3, q1Answer4));

        Question question2 = new Question(2, "Какое море наиболее подходит для жизни дельфинов ?");
        Variant q2Answer1 = new Variant(5, 1, "Черное", 0.3);
        Variant q2Answer2 = new Variant(6, 2, "Баренцево", 0.0);
        Variant q2Answer3 = new Variant(7, 3, "Желтое", 0.1);
        Variant q2Answer4 = new Variant(8, 4, "Средиземное", 0.4);
        Variant q2Answer5 = new Variant(9, 5, "Карибское", 0.2);
        question2.setVariants(Arrays.asList(q2Answer1, q2Answer2, q2Answer3, q2Answer4, q2Answer5));

        Question question3 = new Question(3, "Какая валюта, на взгляд мировых экспертов, имеет наилучший" +
                " дизайн ?");
        Variant q3Answer1 = new Variant(10, 1, "Гривна", 0.4);
        Variant q3Answer2 = new Variant(11, 2, "Евро", 0.3);
        Variant q3Answer3 = new Variant(12, 3, "Доллар США", 0.1);
        Variant q3Answer4 = new Variant(13, 4, "Британский фунт", 0.1);
        Variant q3Answer5 = new Variant(14, 5, "Болгарский лев", 0.1);
        question3.setVariants(Arrays.asList(q3Answer1, q3Answer2, q3Answer3, q3Answer4, q3Answer5));

        Question question4 = new Question(4, "Какой, на взгляд студента, должна быть лекция преподавателя ?");
        Variant q4Answer1 = new Variant(15, 1, "Профессиональной", 0.2);
        Variant q4Answer2 = new Variant(16, 2, "Содержательной", 0.2);
        Variant q4Answer3 = new Variant(17, 3, "Веселой", 0.3);
        Variant q4Answer4 = new Variant(18, 4, "С примерами", 0.2);
        Variant q4Answer5 = new Variant(19, 5, "С анекдотами", 0.1);
        question4.setVariants(Arrays.asList(q4Answer1, q4Answer2, q4Answer3, q4Answer4, q4Answer5));

        Question question5 = new Question(5, "Кто из Нобелевских лауреатов внес наибольший вклад в науку ?");
        Variant q5Answer1 = new Variant(20, 1, "Капица", 0.1);
        Variant q5Answer2 = new Variant(21, 2, "Бор", 0.2);
        Variant q5Answer3 = new Variant(22, 3, "Резерфорд", 0.2);
        Variant q5Answer4 = new Variant(23, 4, "Алферов", 0.1);
        Variant q5Answer5 = new Variant(24, 5, "Тесла", 0.4);
        question5.setVariants(Arrays.asList(q5Answer1, q5Answer2, q5Answer3, q5Answer4, q5Answer5));

        Expert expert1 = new Expert(1, "John");
        ExpertAnswer expert1Answer1 = new ExpertAnswer(1, question1, Arrays.asList(q1Answer1, q1Answer2, q1Answer4));
        ExpertAnswer expert1Answer2 = new ExpertAnswer(2, question2, Arrays.asList(q2Answer1, q2Answer2, q2Answer3));
        ExpertAnswer expert1Answer3 = new ExpertAnswer(3, question3, Arrays.asList(q3Answer1, q3Answer2, q3Answer3));
        ExpertAnswer expert1Answer4 = new ExpertAnswer(4, question4, Arrays.asList(q4Answer3));
        ExpertAnswer expert1Answer5 = new ExpertAnswer(5, question5, Arrays.asList(q5Answer1, q5Answer4, q5Answer5));
        expert1.setAnswer(Arrays.asList(expert1Answer1, expert1Answer2, expert1Answer3, expert1Answer4, expert1Answer5));

        Expert expert2 = new Expert(2, "Sam");
        ExpertAnswer expert2Answer1 = new ExpertAnswer(6, question1, Arrays.asList(q1Answer2, q1Answer3));
        ExpertAnswer expert2Answer2 = new ExpertAnswer(7, question2, Arrays.asList(q2Answer1, q2Answer2));
        ExpertAnswer expert2Answer3 = new ExpertAnswer(8, question3, Arrays.asList(q3Answer2, q3Answer3, q3Answer4));
        ExpertAnswer expert2Answer4 = new ExpertAnswer(9, question4, Arrays.asList(q4Answer3, q4Answer4, q4Answer5));
        ExpertAnswer expert2Answer5 = new ExpertAnswer(10, question5, Arrays.asList(q5Answer2, q5Answer3));
        expert2.setAnswer(Arrays.asList(expert2Answer1, expert2Answer2, expert2Answer3, expert2Answer4, expert2Answer5));

        Expert expert3 = new Expert(3, "Bill");
        ExpertAnswer expert3Answer1 = new ExpertAnswer(11, question1, Arrays.asList(q1Answer1, q1Answer2));
        ExpertAnswer expert3Answer2 = new ExpertAnswer(12, question2, Arrays.asList(q2Answer2, q2Answer3));
        ExpertAnswer expert3Answer3 = new ExpertAnswer(13, question3, Arrays.asList(q3Answer1, q3Answer4));
        ExpertAnswer expert3Answer4 = new ExpertAnswer(14, question4, Arrays.asList(q4Answer2, q4Answer5));
        ExpertAnswer expert3Answer5 = new ExpertAnswer(15, question5, Arrays.asList(q5Answer4, q5Answer5));
        expert3.setAnswer(Arrays.asList(expert3Answer1, expert3Answer2, expert3Answer3, expert3Answer4, expert3Answer5));

        Expert expert4 = new Expert(4, "Pete");
        ExpertAnswer expert4Answer1 = new ExpertAnswer(16, question1, Arrays.asList(q1Answer2, q1Answer4));
        ExpertAnswer expert4Answer2 = new ExpertAnswer(17, question2, Arrays.asList(q2Answer1, q2Answer3, q2Answer4));
        ExpertAnswer expert4Answer3 = new ExpertAnswer(18, question3, Arrays.asList(q3Answer2, q3Answer3));
        ExpertAnswer expert4Answer4 = new ExpertAnswer(19, question4, Arrays.asList(q4Answer3, q4Answer4));
        ExpertAnswer expert4Answer5 = new ExpertAnswer(20, question5, Arrays.asList(q5Answer1, q5Answer4, q5Answer5));
        expert4.setAnswer(Arrays.asList(expert4Answer1, expert4Answer2, expert4Answer3, expert4Answer4, expert4Answer5));


        List<Expert> experts = Arrays.asList(expert1, expert2, expert3, expert4);

        ExpertCompetenceEngine engine = new ExpertCompetenceEngineImpl();

//        List<ExpertCompetence> expertCompetences = engine.countExpertCompetence(experts, 5);


    }
}
