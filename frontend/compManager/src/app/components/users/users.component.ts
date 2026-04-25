import { Component, OnDestroy, OnInit } from '@angular/core';
import { AlertService } from '../../shared/components/alert/alert.service';
import { UserService } from './user.service';
import { Subscription } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { AddUserData, UserAddModalComponent } from '../modals/user-add-modal.component';
import { JudgeRequest } from '../../models/user/Judge';
import { Coach, CoachRequest } from '../../models/user/Coach';
import { CompetitorRequest } from '../../models/user/Competitor';

@Component({
  selector: 'app-users',
  imports: [AsyncPipe, MatTableModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss',
})
export class UsersComponent implements OnInit, OnDestroy {
  private subs = new Subscription();
  protected coachColumns = ['firstName', 'lastName', 'team', 'actions'];
  protected judgesColumns = ['firstName', 'lastName', 'licenseNumber', 'actions'];
  protected competitorColumns = ['firstName', 'lastName', 'weightCategory', 'ageCategory', 'gender', 'actions'];

  protected coaches$;
  protected judges$;
  protected competitors$;

  protected EmptyCoachBody: AddUserData = {
    userType: 'coach',
    mode: 'add',
    firstName: '',
    lastName: '',
    dateOfBirth: new Date(),
    team: ''
  }
  protected EmptyJudgeBody: AddUserData = {
    userType: 'judge',
    mode: 'add',
    firstName: '',
    lastName: '',
    dateOfBirth: new Date(),
    licenseNumber: ''
  }
  protected EmptyCompetitorBody: AddUserData = {
    userType: 'competitor',
    mode: 'add',
    firstName: '',
    lastName: '',
    dateOfBirth: new Date(),
    weightCategory: '',
    ageCategory: '',
    gender: ''
  }

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
        if(res.mode === 'add'){
          this.onAdd(res, data.userType);
        }else if(res.mode === 'edit'){
          this.onEdit(res, data.userType);
        }
      }
    })
  }
  onAdd(req: JudgeRequest | CoachRequest | CompetitorRequest, userType: string) {
    switch (userType) {
      case 'coach': {
        this.userService.addNewCoach(req as CoachRequest).subscribe();
        break;
      };
      case 'judge': {
        this.userService.addNewJudge(req as JudgeRequest).subscribe();
        break;
      };
      case 'competitor': {
        this.userService.addNewCompetitor(req as CompetitorRequest).subscribe();
        break;
      }
    }
  }
  onEdit(req: any, userType: string){
    switch (userType) {
      case 'coach': {
        this.userService.editCoach(req.id, req as CoachRequest).subscribe();
        break;
      };
      case 'judge': {
        this.userService.editJudge(req.id, req as JudgeRequest).subscribe();
        break;
      };
      case 'competitor': {
        this.userService.editCompetitor(req.id, req as CompetitorRequest).subscribe();
        break;
      }
    }
  }
  deleteCoach(coachId: string) {
    this.userService.deleteCoach(coachId).subscribe({
      next: () => {
        this.userService.getAllCoaches().subscribe();
      }
    })
  }
  deleteJudge(judgeId: string) {
    this.userService.deleteJudge(judgeId).subscribe({
      next: () => {
        this.userService.getAllJudges().subscribe();
      }
    })
  }
  deleteCompetitor(competitorId: string) {

    this.userService.deleteCompetitor(competitorId).subscribe({
      next: () => {
        this.userService.getAllCompetitor().subscribe();
      }
    })

  }
  ngOnDestroy(): void {
    this.subs.unsubscribe();
  };
}