import { Question, QuestionVariant } from './question'

export class ExpertAnswer {
    id: number;
    question: Question;
    chosenVariants: QuestionVariant[]
}