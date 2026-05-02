import { JudgeResponse } from '../user/Judge';
import { CompetitorResponse } from '../user/Competitor';

export interface EventRequest {
    name: string;
    rank: string;
    location: string;
    startDate: string;
    judges: string[];
    competitors: string[];
}

export interface EventResponse {
    id: string;
    name: string;
    rank: string;
    location: string;
    startDate: string;
}

export interface EventWithFullData {
    id: string;
    name: string;
    rank: string;
    location: string;
    startDate: string;
    judges: JudgeResponse[];
    competitors: CompetitorResponse[];
}
