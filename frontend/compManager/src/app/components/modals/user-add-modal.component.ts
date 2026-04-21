import { Component, inject } from "@angular/core";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from "@angular/material/dialog";

export interface AddUserData {
    userType: 'coach' | 'judge' | 'competitor';
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
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            dateOfBirth: [null, Validators.required],
            team: (this.data.userType === 'coach') ? [''] : null,
            licenseNumber: (this.data.userType === 'judge') ? ['', Validators.required] : null,
            weightCategory: (this.data.userType === 'competitor') ? ['', Validators.required] : null,
            ageCategory: (this.data.userType === 'competitor') ? ['', Validators.required] : null,
            gender: (this.data.userType === 'competitor') ? ['', Validators.required] : null
        })
    }


    public save() {
        this.ref.close()
    }
    public cancel() {
        this.ref.close({ cancel: true });
    }

    protected controlIsInitialized(controlName: string){
        return this.group.get(controlName) !== null;
    }
}