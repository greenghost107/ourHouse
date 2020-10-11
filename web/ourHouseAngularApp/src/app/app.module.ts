import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DialogModule} from 'primeng/dialog';
import { JwtHelperService } from "@auth0/angular-jwt";
import {CardModule} from 'primeng/card';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {ToastModule} from 'primeng/toast';

// used to create fake backend
import { fakeBackendProvider } from './_helpers';
import {MessageService} from 'primeng/api';
import { appRoutingModule } from './app.routing';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { AppComponent } from './app.component';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AlertComponent } from './_components';;
import { GrocerylistComponent } from './grocerylist/grocerylist.component';;
import { HouseComponent } from './house/house.component'


@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        appRoutingModule,
        DialogModule,
        BrowserAnimationsModule,
        CardModule,
        OverlayPanelModule,
        ProgressSpinnerModule,
        ToastModule
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        AlertComponent
,
        GrocerylistComponent
,
        HouseComponent           ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        JwtHelperService,
        MessageService,
        // provider used to create fake backend
        fakeBackendProvider
    ],
    bootstrap: [AppComponent]
})
export class AppModule { };