export class User{
    private _id : string;
    private _firstName: string;
    private _lastName: string;
    private _dateOfBirth: Date;

    constructor(id: string, firstName: string, lastName: string, dateOfBirth: Date){
        this._id = id;
        this._firstName = firstName;
        this._lastName = lastName;
        this._dateOfBirth = dateOfBirth;
    }

    public get id(){
        return this._id;
    }
    public get firstName(){
        return this._firstName;
    }
    public set firstName(newName: string){
        this._firstName = newName; 
    }

    public get lastName(){
        return this._lastName;
    }
    public set lastName(newLastName: string){
        this._lastName = newLastName;
    }

    public get dateOfBirth(){
        return this._dateOfBirth;
    }
    public set dateOfBirth(newDate: Date){
        this._dateOfBirth = newDate;
    }
}