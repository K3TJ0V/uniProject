import { inject, Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { CoachResponse } from "../../models/user/Coach";
import { JudgeRequest, JudgeResponse } from "../../models/user/Judge";
import { CompetitorRequest, CompetitorResponse } from "../../models/user/Competitor";
import { HttpClient } from "@angular/common/http";

const baseUrl = 'http://localhost:8080/api';

@Injectable({providedIn: 'root'})
export class UserService{
    private http = inject(HttpClient);

    private coaches = new BehaviorSubject<CoachResponse[]>([]);
    private judges = new BehaviorSubject<JudgeRequest[]>([]);
    private competitors = new BehaviorSubject<CompetitorRequest[]>([]);

    public coaches$ = this.coaches.asObservable();
    public judges$ = this.judges.asObservable();
    public competitors$ = this.competitors.asObservable();

    public getAllCoaches() : Observable<CoachResponse[]>{
        return this.http.get<CoachResponse[]>(`${baseUrl}/coach`)
            .pipe(
                tap(data => {
                    this.coaches.next(data);
                })
            );
    }
    public getAllJudges() : Observable<JudgeResponse[]>{
        return this.http.get<JudgeResponse[]>(`${baseUrl}/judge`)
            .pipe(
                tap(data => {
                    this.judges.next(data);
                })
            );
    }
    public getAllCompetitor() : Observable<CompetitorResponse[]>{
        return this.http.get<CompetitorResponse[]>(`${baseUrl}/competitor`)
            .pipe(
                tap(data => {
                    this.competitors.next(data);
                })
            );
    }
}