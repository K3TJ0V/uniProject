import { User } from "./User";

export class Competitor extends User {
    private _ageCategory: string;
    private _weightCategory: string;
    private _gender: string;

    constructor(id: string, firstName: string, lastName: string, dateOfBirth: Date, ageCategory: string, weightCategory: string, gender: string) {
        super(id, firstName, lastName, dateOfBirth);
        this._ageCategory = ageCategory;
        this._weightCategory = weightCategory;
        this._gender = gender;
    }

    public get ageCategory() {
        return this._ageCategory;
    }
    public set ageCategory(newCategopry: string) {
        this._ageCategory = newCategopry;
    }
    public get weightCategory() {
        return this._weightCategory;
    }
    public set weightCategory(newCategory: string) {
        this._weightCategory = newCategory;
    }
    public get gender() {
        return this._gender;
    }
    public set gender(newGender: string) {
        this._gender = newGender;
    }
}

export interface CompetitorRequest {
    firstName: string;
    lastName: string;
    weightCategory: string;
    ageCategory: string;
    gender: string;
    dateOfBirth: Date;
}
export interface CompetitorResponse {
    id: string;
    firstName: string;
    lastName: string;
    weightCategory: string;
    ageCategory: string;
    gender: string;
    dateOfBirth: Date;
}