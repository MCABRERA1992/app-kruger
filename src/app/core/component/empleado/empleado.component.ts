import { IfStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { finalize } from 'rxjs';
import { Empleado } from '../../models/empleado';
import { EmpleadoDetalle } from '../../models/empleado-detalle';
import { EstadoVacunado } from '../../models/estado-vacunado';
import { TipoVacuna } from '../../models/tipo-vacuna';
import { StorageService } from '../../services/storage.service';
import { EmpleadoService } from './empleado.service';

@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css']
})
export class EmpleadoComponent implements OnInit {

  formularioEmpleado: FormGroup;
  formularioEmpleadoFiltro: FormGroup;
  public banderaRol: Boolean = true;
  public idEmpleado: number = 0;
  public listEmpleados: EmpleadoDetalle[] = [];

  constructor(private router: Router,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private empleadoService: EmpleadoService,
    private toastr: ToastrService,
    private storageService: StorageService) {

    this.formularioEmpleado = this.fb.group({
      fbNombre: [{ value: '', disabled: false }],
      fbApellido: [{ value: '', disabled: false }],
      fbCedula: [{ value: '', disabled: false }],
      fbFechaNacimiento: [{ value: '', disabled: false }],
      fbCorreoElectronico: [{ value: '', disabled: false }],
      fbDireccionDomicilio: [{ value: '', disabled: false }],
      fbTelefonoMovil: [{ value: '', disabled: false }],
      fbEstadoVacuna: [{ value: '', disabled: false }],
      fbTIpoVacuna: [{ value: '', disabled: false }],
      fbFechaVacuna: [{ value: '', disabled: false }],
      fbNUmeroDosis: [{ value: '', disabled: false }]
    });


    this.formularioEmpleadoFiltro = this.fb.group({
      fbEstadoVacunaFiltro: [{ value: '', disabled: false }],
      fbTIpoVacunaFiltro: [{ value: '', disabled: false }],
      fbFechaInicio: [{ value: '', disabled: false }],
      fbFechaFin: [{ value: '', disabled: false }]
    });
  }


  ngOnInit(): void {
    this.loadCamposRolUsuario();
  }

  loadCamposRolUsuario() {
    const userRol = this.storageService.obtenerAuthorities();
    userRol.forEach(rol => {
      if (rol === 'ROL_EMPLEADO') {
        this.banderaRol = false;
        this.onLoadDefaultEmpleado(rol);
      } else {
        this.loadListEmpleado();
        this.formularioEmpleado.get('fbFechaNacimiento')?.disable();
        this.formularioEmpleado.get('fbDireccionDomicilio')?.disable();
        this.formularioEmpleado.get('fbTelefonoMovil')?.disable();
        this.formularioEmpleado.get('fbEstadoVacuna')?.disable();
      }
    });
  }

  onLoadDefaultEmpleado(rolUser: string): void {

    this.spinner.show('spinner-capacitacion');
    this.empleadoService.filtrosEmpleadosRol(this.storageService.getUsuario(), rolUser).pipe(finalize(() => this.spinner.hide('spinner-capacitacion')))
      .subscribe(response => {
        if (response) {

          this.idEmpleado = response.id
          this.formularioEmpleado.get('fbNombre')?.setValue(response.nombre);
          this.formularioEmpleado.get('fbApellido')?.setValue(response.apellido);
          this.formularioEmpleado.get('fbCedula')?.setValue(response.cedula);
          this.formularioEmpleado.get('fbFechaNacimiento')?.setValue(response.fechaNacimiento);
          this.formularioEmpleado.get('fbCorreoElectronico')?.setValue(response.correoElectronico);
          this.formularioEmpleado.get('fbDireccionDomicilio')?.setValue(response.direccionDomicilio);
          this.formularioEmpleado.get('fbTelefonoMovil')?.setValue(response.telefonoMovil);
          this.formularioEmpleado.get('fbEstadoVacuna')?.setValue(response.estadoVacunaDto.estadoVacuna);
          this.formularioEmpleado.get('fbTIpoVacuna')?.setValue(response.tipoVacunaDto.tipoVacunacion);
          this.formularioEmpleado.get('fbFechaVacuna')?.setValue(response.tipoVacunaDto.fechaVacunacion);
          this.formularioEmpleado.get('fbNUmeroDosis')?.setValue(response.tipoVacunaDto.numeroDosis);

          this.formularioEmpleado.get('fbNombre')?.disable();
          this.formularioEmpleado.get('fbApellido')?.disable();
          this.formularioEmpleado.get('fbCedula')?.disable();
          this.formularioEmpleado.get('fbCorreoElectronico')?.disable();

        } else {
          this.toastr.warning('Advertencia', response === null ? 'No Existen datos para consultar' : response.valor);
        }
      }, error => {
        this.toastr.error('Error', 'Error al cargar los dato del empleado ');
      });
  }

  logout(): void {
    window.sessionStorage.clear();
    this.router.navigate(['/login'])
  }

  guardarEmpleado(bandera: string): void {

    if (bandera === 'I') {
      if (this.formularioEmpleado.get('fbNombre')?.value === undefined ||
        this.formularioEmpleado.get('fbNombre')?.value === null ||
        this.formularioEmpleado.get('fbNombre')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar un nombre, no puede estar vacio');
        return;
      }
      if (this.formularioEmpleado.get('fbApellido')?.value === undefined ||
        this.formularioEmpleado.get('fbApellido')?.value === null ||
        this.formularioEmpleado.get('fbApellido')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar un apellido, no puede estar vacio');
        return;
      }
      if (this.formularioEmpleado.get('fbCedula')?.value === undefined ||
        this.formularioEmpleado.get('fbCedula')?.value === null ||
        this.formularioEmpleado.get('fbCedula')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar una cedula, no puede estar vacio');
        return;
      }
      if (this.formularioEmpleado.get('fbCorreoElectronico')?.value === undefined ||
        this.formularioEmpleado.get('fbCorreoElectronico')?.value === null ||
        this.formularioEmpleado.get('fbCorreoElectronico')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar un correo electronico, no puede estar vacio');
        return;
      }

    } else if (bandera === 'A') {
      if (this.formularioEmpleado.get('fbFechaNacimiento')?.value === undefined ||
        this.formularioEmpleado.get('fbFechaNacimiento')?.value === null ||
        this.formularioEmpleado.get('fbFechaNacimiento')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar una fecha de nacimiento, no puede estar vacio');
        return;
      }
      if (this.formularioEmpleado.get('fbDireccionDomicilio')?.value === undefined ||
        this.formularioEmpleado.get('fbDireccionDomicilio')?.value === null ||
        this.formularioEmpleado.get('fbDireccionDomicilio')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar un direccion de domicilio, no puede estar vacio');
        return;
      }
      if (this.formularioEmpleado.get('fbTelefonoMovil')?.value === undefined ||
        this.formularioEmpleado.get('fbTelefonoMovil')?.value === null ||
        this.formularioEmpleado.get('fbTelefonoMovil')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar un telefono movil, no puede estar vacio');
        return;
      }
      if (this.formularioEmpleado.get('fbEstadoVacuna')?.value === undefined ||
        this.formularioEmpleado.get('fbEstadoVacuna')?.value === null ||
        this.formularioEmpleado.get('fbEstadoVacuna')?.value === '') {
        this.toastr.warning('Advertencia', 'Seleccionar un estado de vacuna, no puede estar vacio');
        return;
      }

      if (this.formularioEmpleado.get('fbEstadoVacuna')?.value === 'Vacunado' && this.formularioEmpleado.get('fbTIpoVacuna')?.value === '') {
        this.toastr.warning('Advertencia', 'Seleccionar un tipo de vacuna, no puede estar vacio');
        return;
      }
      if (this.formularioEmpleado.get('fbEstadoVacuna')?.value === 'Vacunado' && this.formularioEmpleado.get('fbFechaVacuna')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar una fecha de vacunacion, no puede estar vacio');
        return;
      }
      if (this.formularioEmpleado.get('fbEstadoVacuna')?.value === 'Vacunado' && this.formularioEmpleado.get('fbNUmeroDosis')?.value === '') {
        this.toastr.warning('Advertencia', 'Ingresar numero de dosis, no puede estar vacio');
        return;
      }
    }

    let estadoVacuna: EstadoVacunado = {
      id: 0,
      estadoVacuna: this.formularioEmpleado.get('fbEstadoVacuna')?.value
    }

    let tipoVacuna: TipoVacuna = {
      fechaVacunacion: this.formularioEmpleado.get('fbFechaVacuna')?.value,
      numeroDosis: this.formularioEmpleado.get('fbNUmeroDosis')?.value,
      tipoVacunacion: this.formularioEmpleado.get('fbTIpoVacuna')?.value
    }

    let empleado: Empleado = {
      id: this.idEmpleado,
      nombre: this.formularioEmpleado.get('fbNombre')?.value,
      apellido: this.formularioEmpleado.get('fbApellido')?.value,
      cedula: this.formularioEmpleado.get('fbCedula')?.value,
      fechaNacimiento: this.formularioEmpleado.get('fbFechaNacimiento')?.value,
      correoElectronico: this.formularioEmpleado.get('fbCorreoElectronico')?.value,
      direccionDomicilio: this.formularioEmpleado.get('fbDireccionDomicilio')?.value,
      telefonoMovil: this.formularioEmpleado.get('fbTelefonoMovil')?.value,
      estadoVacunaDto: estadoVacuna,
      tipoVacunaDto: tipoVacuna
    }


    this.spinner.show('spinner-capacitacion');
    this.empleadoService.saveOrUpdateEmpleados(empleado).pipe(finalize(() => this.spinner.hide('spinner-capacitacion')))
      .subscribe(response => {
        if (response) {
          this.toastr.success('Exito', response.valor);
          if (bandera === 'A') {
            this.onLoadFiltrosEmpleado();
          } else {
            this.formularioEmpleado.reset();
            this.loadListEmpleado();
          }
        } else {
          this.toastr.warning('Advertencia', response.valor);
        }
      }, error => {
        this.toastr.error('Error', 'error al guardar datos del empleado');
      });
  }


  loadListEmpleado(): void {
    this.spinner.show('spinner-capacitacion');
    this.empleadoService.listarEmpleados().pipe(finalize(() => this.spinner.hide('spinner-capacitacion')))
      .subscribe(response => {
        if (response) {
          this.listEmpleados = response;
        } else {
          this.toastr.warning('Advertencia', response === null ? 'No Existen datos para consultar' : response.valor);
        }
      }, error => {
        this.toastr.error('Error', 'Error al cargar la lista de los empleados');
      });
  }


  eliminarEmpleado(id: number): void {
    this.spinner.show('spinner-capacitacion');
    this.empleadoService.deleteEmpleado(id).pipe(finalize(() => this.spinner.hide('spinner-capacitacion')))
      .subscribe(response => {
        if (response) {
          this.toastr.success('Exitos', response.valor);
          this.loadListEmpleado();
        } else {
          this.toastr.warning('Advertencia', response.valor);
        }
      }, error => {
        this.toastr.error('Error', 'Error al cargar la lista de los empleados');
      });
  }

  onLoadFiltrosEmpleado() {

    this.spinner.show('spinner-capacitacion');
    this.empleadoService.filtrosEmpleados(this.formularioEmpleadoFiltro.get('fbEstadoVacunaFiltro')?.value,
      this.formularioEmpleadoFiltro.get('fbTIpoVacunaFiltro')?.value,
      this.formularioEmpleadoFiltro.get('fbFechaInicio')?.value,
      this.formularioEmpleadoFiltro.get('fbFechaFin')?.value)
      .pipe(finalize(() => this.spinner.hide('spinner-capacitacion')))
      .subscribe(response => {
        if (response) {
          this.listEmpleados = response;
        } else {
          this.toastr.warning('Advertencia', 'No hay datos en base a los filtros de busqueda');
          this.listEmpleados = [];
        }
      }, error => {
        this.toastr.error('Error', 'Error al cargar la lista de los empleados');
      });
  }

}
