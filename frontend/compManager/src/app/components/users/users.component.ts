import { Component, inject } from '@angular/core';
import { AlertService } from '../../shared/components/alert/alert.service';

@Component({
  selector: 'app-users',
  imports: [],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss',
})
export class UsersComponent {
  alertService = inject(AlertService);

}
