import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Empleado } from '../../models/empleado';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  constructor(private httpClient: HttpClient) { }

  public listarEmpleados(): Observable<any> {
    return this.httpClient.get<any>(`${environment.appKruger}/empleado-controller/listar-empleado`);
  }

  public deleteEmpleado(id: number): Observable<any> {
    return this.httpClient.get<any>(`${environment.appKruger}/empleado-controller/delete-empleado?id=` + id);
  }

  public filtrosEmpleados(estadoVacuna: string, tipoVacuna: string, fechaInicio: string, fechaFin: string): Observable<any> {
    return this.httpClient.get<any>(`${environment.appKruger}/empleado-controller/filtros-empleado?estadoVacuna=` + estadoVacuna + `&tipoVacuna=` + tipoVacuna + `&fechaInicio=` + fechaInicio+`&fechaFin=`+fechaFin);
  }

  public saveOrUpdateEmpleados(empleado: Empleado): Observable<any> {
    return this.httpClient.post<any>(`${environment.appKruger}/empleado-controller/saveOrUpdate-empleado`, empleado);
  }

  public filtrosEmpleadosRol(user:string, rolUser: string): Observable<any> {
    return this.httpClient.get<any>(`${environment.appKruger}/empleado-controller/filtrar-empleado-rol?user=` + user+`&rolUser=`+rolUser);
  }
}
