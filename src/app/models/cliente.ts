import { Venta } from './venta';

export class Cliente {
    id: number;
    nombres: string;
    apellidos: string;
    dni: string;
    telefono: string;
    email:string;
    ventas: Venta[];
}