import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserSignUpComponent } from './user-sign-up/user-sign-up.component';
import { UserDasboardComponent } from './user-dasboard/user-dasboard.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminDasboardComponent } from './admin-dasboard/admin-dasboard.component';

@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    UserSignUpComponent,
    UserDasboardComponent,
    AdminLoginComponent,
    AdminDasboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {
        path: 'AadharApp/citizens/logIn',
        pathMatch: 'full',
        component: UserLoginComponent,
      },
      {
        path: 'AadharApp/citizens/signUp',
        pathMatch: 'full',
        component: UserSignUpComponent,
      },
      {
        path: 'AadharApp/citizens/dashboard',
        pathMatch: 'full',
        component: UserDasboardComponent,
      },
      {
        path: 'AadharApp/admin/logIn',
        pathMatch: 'full',
        component: AdminLoginComponent,
      },
      {
        path: 'AadharApp/admin/dashboard',
        pathMatch: 'full',
        component: AdminDasboardComponent,
      },
      { path: '**', component: UserLoginComponent },
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
