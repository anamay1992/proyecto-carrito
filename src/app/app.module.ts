import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { VentaComponent } from './components/venta/venta.component';
import { FormComponent } from './components/venta/form.component';
import { VentaService } from './services/venta.service';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
  { path: 'ventas', component: VentaComponent },

]

@NgModule({
  declarations: [
    AppComponent,
    VentaComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [
    VentaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
