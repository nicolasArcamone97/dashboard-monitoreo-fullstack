import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { RegisterComponent } from './pages/register/register.component';
import { authGuard } from './auth.guard';

export const routes: Routes = [
    {path: 'dashboard', component:DashboardComponent, pathMatch:'full', canActivate:[authGuard]},
    {path:'login', component:LoginComponent, pathMatch:'full'},
    {path:'register', component:RegisterComponent, pathMatch:'full'},
    { path: '', redirectTo: '/register', pathMatch: 'full' }
];
