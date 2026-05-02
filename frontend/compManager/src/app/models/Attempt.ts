export type Discipline = 'SNATCH' | 'CLEAN_AND_JERK';
export type AttemptResult = 'VALID' | 'NO_LIFT' | 'PENDING';

export interface DecisionRequest {
    judgeId: string;
    valid: boolean;
}

export interface AttemptRequest {
    competitorId: string;
    discipline: Discipline;
    attemptNumber: 1 | 2 | 3;
    declaredWeight: number | null;
    decisions: DecisionRequest[];
}

export interface DecisionResponse {
    judgeId: string;
    judgeName: string;
    valid: boolean;
}

export interface AttemptResponse {
    id: string;
    competitorId: string;
    competitorFirstName: string;
    competitorLastName: string;
    discipline: Discipline;
    attemptNumber: 1 | 2 | 3;
    declaredWeight: number | null;
    decisions: DecisionResponse[];
    result: AttemptResult;
}
