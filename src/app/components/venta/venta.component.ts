import { Component, OnInit, NgModule } from '@angular/core';
import { VentaService } from 'src/app/services/venta.service';
import { Venta } from '../../models/venta';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html'
})
export class VentaComponent implements OnInit {

  venta: Venta = null;
  ventas: Venta[];

  constructor(private ventaService: VentaService) { }

  ngOnInit(): void {
    this.ventaService.getVentas().subscribe((ventas:any) => {
      this.ventas = ventas.response
    });
  }

  getVenta(id): void {
    console.log(id)
    this.ventaService.getVenta(id).subscribe((venta:any) => {
      this.venta = venta.response; 
    });
  }

  getVentaByFecha(fecha): void {
    var date = fecha.target.value;
    this.ventaService.getVentaByFecha(String(date)).subscribe((ventas:any) => {
      this.ventas = ventas.response;
    });
  }

}
