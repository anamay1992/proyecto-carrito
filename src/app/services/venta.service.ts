import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venta } from '../models/venta';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private urlEndPoint: string = "http://localhost:8080/api/ventas";

  private urlEndPoint2: string = "http://localhost:8080/api/ventasPorFecha";

  //private httpHeaders = new HttpHeaders({ 'Content-type': 'application/json' });

  constructor(private http: HttpClient) { }

  getVentas(): Observable<Venta[]> {
    return this.http.get(this.urlEndPoint).pipe(
      map( response => response as Venta[] )
    );
  }

  getVenta(id): Observable<Venta> {
    return this.http.get(`${this.urlEndPoint}/${id}`);
  }

  getVentaByFecha(fecha: string): Observable<Venta> {
    return this.http.get(`${this.urlEndPoint2}/${fecha}`);
  }

}
