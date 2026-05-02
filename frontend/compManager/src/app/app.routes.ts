import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UsersComponent } from './components/users/users.component';
import { EventCreatorComponent } from './components/event/event-creator/event-creator.component';
import { EventListComponent } from './components/event/event-list/event-list.component';
import { EventManagerComponent } from './components/event/event-manager/event-manager.component';

export const routes: Routes = [
    {path: "", component: HomeComponent},
    {path: "users", component: UsersComponent},
    {path: "events", component: EventListComponent},
    {path: "event-creator", component: EventCreatorComponent},
    {path: "event-manager/:id", component: EventManagerComponent},
];
