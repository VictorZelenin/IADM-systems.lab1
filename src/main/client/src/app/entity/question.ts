export class Question {
    id: number;
    description: string;
    variants: QuestionVariant[];
  }
  
export interface QuestionVariant {
    id: number;
    index: number;
    description: string;
    value: number;
}