import { Component, inject, OnDestroy } from '@angular/core';
import { AlertService } from '../../shared/components/alert/alert.service';
import { UserService } from './user.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-users',
  imports: [],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss',
})
export class UsersComponent implements OnDestroy{
  private subs = new Subscription();

  private alertService = inject(AlertService);
  private userService = inject(UserService);

  private coaches$ = this.userService.coaches$;


  constructor() {
    this.alertService.error("test")
    this.userService.getAllCoaches().subscribe();
    const coachSub = this.coaches$.subscribe({
      next: (data) => {
        console.log(data);
      }
    })
    this.subs.add(coachSub);
  }

  ngOnDestroy(): void {
    this.subs.unsubscribe();
  }
}
