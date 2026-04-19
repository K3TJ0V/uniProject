import { User } from "./User";

export class Judge extends User {
    private _licenseNumber: string;

    constructor(id: string, firstName: string, lastName: string, dateOfBirth: Date, licenseNumber: string){
        super(id, firstName, lastName, dateOfBirth);
        this._licenseNumber = licenseNumber;
    }

    public get licenseNumber(){
        return this._licenseNumber;
    }
    public set licenseNumber(newLicenseNumber: string){
        this._licenseNumber = newLicenseNumber;
    }
}

export interface JudgeRequest{
    firstName: string;
    lastName: string;
    dateOfBirth: Date;
    licenseNumber: string;
}

export interface JudgeResponse{
    id: string;
    firstName: string;
    lastName: string;
    dateOfBirth: Date;
    licenseNumber: string;
}