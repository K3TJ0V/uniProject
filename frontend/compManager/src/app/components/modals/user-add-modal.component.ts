import { Component, inject } from "@angular/core";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from "@angular/material/dialog";
import { JudgeRequest } from "../../models/user/Judge";
import { CoachRequest } from "../../models/user/Coach";
import { CompetitorRequest } from "../../models/user/Competitor";

export interface AddUserData {
    userType: 'coach' | 'judge' | 'competitor';
    mode: 'edit' | 'add',
    id?: string,
    firstName: string,
    lastName: string,
    dateOfBirth: Date,
    team? : string,
    licenseNumber? : string,
    weightCategory? : string,
    ageCategory? : string,
    gender? : string
}

@Component({
    selector: 'add-users-modal',
    imports: [MatDialogModule, ReactiveFormsModule],
    templateUrl: './user-add-modal.component.html',
    styleUrl: './user-add-modal.component.scss',
})
export class UserAddModalComponent {
    private ref = inject(MatDialogRef<UserAddModalComponent>)
    private fb = inject(FormBuilder);
    protected data = inject<AddUserData>(MAT_DIALOG_DATA);

    protected group;

    constructor() {
        this.group = this.fb.group({
            firstName: [this.data ? this.data.firstName : '', Validators.required],
            lastName: [this.data ? this.data.lastName : '', Validators.required],
            dateOfBirth: [this.data ? this.data.dateOfBirth : new Date(), Validators.required],
            team: (this.data.userType === 'coach') ? [this.data.team ? this.data.team : ''] : null,
            licenseNumber: (this.data.userType === 'judge') ? [this.data.licenseNumber ? this.data.licenseNumber : '', Validators.required] : null,
            weightCategory: (this.data.userType === 'competitor') ? [this.data.weightCategory ? this.data.weightCategory : '', Validators.required] : null,
            ageCategory: (this.data.userType === 'competitor') ? [this.data.ageCategory ? this.data.ageCategory : '', Validators.required] : null,
            gender: (this.data.userType === 'competitor') ? [this.data.gender ? this.data.gender : '', Validators.required] : null
        })
    }


    public save() {
        if (this.group.invalid) return;

        const raw = this.group.getRawValue();

        const editId = this.data.mode === 'edit' ? { id: this.data.id } : {};

        switch (this.data.userType) {
            case 'judge': {
                const judge: JudgeRequest = {
                    firstName: raw.firstName!,
                    lastName: raw.lastName!,
                    dateOfBirth: raw.dateOfBirth!,
                    licenseNumber: (raw.licenseNumber! as unknown as string) ?? '',
                };
                this.ref.close({ ...judge, ...editId, mode: this.data.mode });
                break;
            }
            case 'coach': {
                const coach: CoachRequest = {
                    firstName: raw.firstName!,
                    lastName: raw.lastName!,
                    dateOfBirth: raw.dateOfBirth!,
                    team: (raw.team! as unknown as string) ?? ''
                }
                this.ref.close({ ...coach, ...editId, mode: this.data.mode });
                break;
            }
            case 'competitor': {
                const competitor: CompetitorRequest = {
                    firstName: raw.firstName!,
                    lastName: raw.lastName!,
                    dateOfBirth: raw.dateOfBirth!,
                    weightCategory: (raw.weightCategory! as unknown as string) ?? '',
                    ageCategory: (raw.ageCategory! as unknown as string) ?? '',
                    gender: (raw.gender! as unknown as string) ?? '',
                }
                this.ref.close({ ...competitor, ...editId, mode: this.data.mode });
                break;
            }
        }
    }

    public cancel() {
        this.ref.close({ cancel: true });
    }

    protected controlIsInitialized(controlName: string) {
        return this.group.get(controlName) !== null;
    }
}