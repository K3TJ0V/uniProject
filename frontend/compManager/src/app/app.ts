import { Component } from '@angular/core';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RouterOutlet } from '@angular/router';
import { AlertComponent } from "./shared/components/alert/alert.component";

@Component({
  selector: 'app-root',
  imports: [DashboardComponent, RouterOutlet, AlertComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {

}
