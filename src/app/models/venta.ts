import { Cliente } from './cliente';
import { VentaDetalle } from './ventaDetalle';

export class Venta {
    id?: number;
    cliente?: Cliente;
    detalles?: VentaDetalle[];
    fecha?: Date;
}