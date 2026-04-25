import { inject, Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { CoachRequest, CoachResponse } from "../../models/user/Coach";
import { JudgeRequest, JudgeResponse } from "../../models/user/Judge";
import { CompetitorRequest, CompetitorResponse } from "../../models/user/Competitor";
import { HttpClient } from "@angular/common/http";

const baseUrl = 'http://localhost:8080/api';

@Injectable({ providedIn: 'root' })
export class UserService {
    private http = inject(HttpClient);

    private coaches = new BehaviorSubject<CoachResponse[]>([]);
    private judges = new BehaviorSubject<JudgeResponse[]>([]);
    private competitors = new BehaviorSubject<CompetitorResponse[]>([]);

    public coaches$ = this.coaches.asObservable();
    public judges$ = this.judges.asObservable();
    public competitors$ = this.competitors.asObservable();

    public getAllCoaches(): Observable<CoachResponse[]> {
        return this.http.get<CoachResponse[]>(`${baseUrl}/coach`)
            .pipe(
                tap(data => {
                    this.coaches.next(data);
                })
            );
    }
    public getAllJudges(): Observable<JudgeResponse[]> {
        return this.http.get<JudgeResponse[]>(`${baseUrl}/judge`)
            .pipe(
                tap(data => {
                    this.judges.next(data);
                })
            );
    }
    public getAllCompetitor(): Observable<CompetitorResponse[]> {
        return this.http.get<CompetitorResponse[]>(`${baseUrl}/competitor`)
            .pipe(
                tap(data => {
                    this.competitors.next(data);
                })
            );
    }
    public addNewCoach(coach: CoachRequest): Observable<CoachResponse> {
        return this.http.post<CoachResponse>(`${baseUrl}/coach`, coach)
            .pipe(
                tap(() => { this.getAllCoaches().subscribe() })
            )
    }
    public deleteCoach(coachId: string): Observable<void> {
        return this.http.delete<void>(`${baseUrl}/coach/${coachId}`)
    }
    public editCoach(coachId: string, coach: CoachRequest): Observable<CoachResponse> {
        return this.http.put<CoachResponse>(`${baseUrl}/coach/${coachId}`, coach)
            .pipe(
                tap(() => { this.getAllCoaches().subscribe() })
            )
    }
    public addNewJudge(judge: JudgeRequest): Observable<JudgeResponse> {
        return this.http.post<JudgeResponse>(`${baseUrl}/judge`, judge)
            .pipe(
                tap(() => { this.getAllJudges().subscribe() })
            )
    }
    public deleteJudge(judgeId: string): Observable<void> {
        return this.http.delete<void>(`${baseUrl}/judge/${judgeId}`)
    }
    public editJudge(judgeId: string, judge: JudgeRequest): Observable<JudgeResponse> {
        return this.http.put<JudgeResponse>(`${baseUrl}/judge/${judgeId}`, judge)
            .pipe(
                tap(() => { this.getAllJudges().subscribe() })
            )
    }
    public addNewCompetitor(competitor: CompetitorRequest): Observable<CompetitorRequest> {
        return this.http.post<CompetitorResponse>(`${baseUrl}/competitor`, competitor)
            .pipe(
                tap(() => { this.getAllCompetitor().subscribe() })
            )
    }
    public deleteCompetitor(competitorId: string): Observable<void> {
        return this.http.delete<void>(`${baseUrl}/competitor/${competitorId}`)
    }
    public editCompetitor(competitorId: string, competitor: CompetitorRequest): Observable<CompetitorResponse> {
        return this.http.put<CompetitorResponse>(`${baseUrl}/competitor/${competitorId}`, competitor)
            .pipe(
                tap(() => { this.getAllCompetitor().subscribe() })
            )
    }
}