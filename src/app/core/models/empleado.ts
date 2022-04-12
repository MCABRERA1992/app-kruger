import { EstadoVacunado } from "./estado-vacunado";
import { TipoVacuna } from "./tipo-vacuna";

export class Empleado {
  apellido!: string;
  cedula!: string;
  correoElectronico!: string;
  direccionDomicilio!: string;
  estadoVacunaDto!: EstadoVacunado;
  fechaNacimiento!: string;
  id!: number;
  nombre!: string;
  telefonoMovil!: string;
  tipoVacunaDto!: TipoVacuna;

}
