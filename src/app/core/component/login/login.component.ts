import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { finalize } from 'rxjs';
import { Jwt } from '../../models/jwt';
import { Usuario } from '../../models/Usuario';
import { StorageService } from '../../services/storage.service';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formularioUser: FormGroup;

  constructor(private router: Router,
    private fb: FormBuilder,
    private authStorage: StorageService,
    private authService: LoginService,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService) {

    this.formularioUser = this.fb.group({
      fbUsuario: [{ value: '', disabled: false }],
      fbClave: [{ value: '', disabled: false }]
    });

  }

  ngOnInit(): void {
  }

  onLogin(): void {

    if (this.formularioUser.get('fbUsuario')?.value === undefined ||
      this.formularioUser.get('fbUsuario')?.value === null ||
      this.formularioUser.get('fbUsuario')?.value === '') {
      this.toastr.warning('Advertencia', 'Ingresar un usuario, no puede estar vacio');
      return;
    }

    if (this.formularioUser.get('fbClave')?.value === undefined ||
      this.formularioUser.get('fbClave')?.value === null ||
      this.formularioUser.get('fbClave')?.value === '') {
      this.toastr.warning('Advertencia', 'Ingresar una clave, no puede estar vacio');
      return;
    }

    const user: Usuario = {
      usuario: this.formularioUser.get('fbUsuario')?.value,
      clave: this.formularioUser.get('fbClave')?.value
    }

    this.spinner.show('spinner-capacitacion');
    this.authService.loginSecurity(user)
      .pipe(finalize(() => this.spinner.hide('spinner-capacitacion')))
      .subscribe(authResponse => {

        let jwtToken: Jwt = {
          bearer: authResponse.bearer,
          nameUser: authResponse.nameUser,
          token: authResponse.token,
          grantedAuthorities: authResponse.grantedAuthorities
        }
        this.authStorage.setToken(jwtToken.token);
        this.authStorage.setUsuario(jwtToken.nameUser);
        this.authStorage.setAuthorities(jwtToken.grantedAuthorities);
        this.router.navigate(["/empleado"]);
      }, error => {
        console.log(error);
        this.toastr.warning('Advertencia', 'Usuario y Clave Incorrecta');
      });
  }

}
