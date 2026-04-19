import { User } from "./User";

export class Coach extends User {
    private _team: string;

    constructor(id: string, firstName: string, lastName: string, dateOfBirth: Date, team: string){
        super(id, firstName, lastName, dateOfBirth);
        this._team = team;
    }

    public get team(){
        return this._team;
    }
    public set team(newTeam: string){
        this._team = newTeam;
    }
}

export interface CoachRequest{
    firstName: string;
    lastName: string;
    dateOfBirth: Date;
    team: string;
}

export interface CoachResponse{
    id: string;
    firstName: string;
    lastName: string;
    dateOfBirth: Date;
    team: string;
}