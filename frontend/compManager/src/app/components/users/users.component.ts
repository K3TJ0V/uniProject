import { Component, OnDestroy, OnInit } from '@angular/core';
import { AlertService } from '../../shared/components/alert/alert.service';
import { UserService } from './user.service';
import { Subscription } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { AddUserData, UserAddModalComponent } from '../modals/user-add-modal.component';

@Component({
  selector: 'app-users',
  imports: [AsyncPipe, MatTableModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss',
})
export class UsersComponent implements OnInit, OnDestroy {
  private subs = new Subscription();
  protected coachColumns = ['firstName', 'lastName', 'team'];
  protected judgesColumns = ['firstName', 'lastName', 'licenseNumber'];
  protected competitorColumns = ['firstName', 'lastName', 'weightCategory', 'ageCategory', 'gender'];

  protected coaches$;
  protected judges$;
  protected competitors$;

  constructor(
    private alertService: AlertService,
    private userService: UserService,
    private dialog: MatDialog
  ) {
    this.coaches$ = this.userService.coaches$,
      this.judges$ = this.userService.judges$,
      this.competitors$ = this.userService.competitors$
  }

  ngOnInit(): void {
    this.userService.getAllCoaches().subscribe({
      error: () => {
        this.alertService.error("Failed to fetch coaches")
      }
    });
    this.userService.getAllJudges().subscribe({
      error: () => {
        this.alertService.error("Failed to fetch judges")
      }
    });
    this.userService.getAllCompetitor().subscribe({
      error: () => {
        this.alertService.error("Failed to fetch competitors")
      }
    });
  }

  open(data: AddUserData) {
    this.dialog.open(UserAddModalComponent, {
      data,
      width: '420px',
      panelClass: 'app-dialog-panel'
    }).afterClosed().subscribe({
      next: (res) => {
        if (!res) { return };
        this.onAdd();
      }
    })
  }
  onAdd() {
    console.log("add")
  }
  ngOnDestroy(): void {
    this.subs.unsubscribe();
  }
}
