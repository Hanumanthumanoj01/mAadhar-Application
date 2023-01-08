import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-admin-dasboard',
  templateUrl: './admin-dasboard.component.html',
  styleUrls: ['./admin-dasboard.component.css'],
})
export class AdminDasboardComponent implements OnInit {
  data = { adminId: localStorage.getItem('adminId') };
  newAadharApplications: any = [];

  constructor(private router: Router, private adminService: AdminService) {}

  ngOnInit(): void {
    if (!this.data.adminId) {
      this.router.navigate(['AadharApp/admin/logIn']);
      return;
    } else {
      this.adminService.getNewAadharDetails().subscribe(
        (res) => {
          if (res) {
            this.newAadharApplications = res;
            console.log(res);
          }
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }

  approveApplication(applicationId: number): void {
    const approveAadharApplication = { applicationId: applicationId };
    this.adminService
      .approveNewAadharApplication(approveAadharApplication)
      .subscribe(
        (res) => {
          if (res) {
            console.log(res);
            window.location.reload();
          }
        },
        (err) => {
          console.log(err);
        }
      );
  }

  signOut(): void {
    localStorage.clear();
    this.router.navigate(['AadharApp/admin/logIn']);
  }
}
