import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {environment} from './../../environments/environment';
import { User } from '@/_models';
import { House } from '@/_models';
import { from } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    // getAll() {
    //     return this.http.get<User[]>(`http://localhost:8080/user`);
    // }

    register(user: User) {
        // return this.http.post(`http://localhost:8080/register`, user);
        return this.http.post(environment.serverUrl + `/register`, user);
    }

    // delete(id: number) {
    //     return this.http.delete(`http://localhost:8080/users/${id}`);
    // }

    joinUserToHouse(user: User,house: House){
        const token = 'Bearer ' +localStorage.getItem("access_token");
        // return this.http.post(`http://localhost:8080/user/setHouse`, house,this.getHttpPostOptions());
        return this.http.post(environment.serverUrl + `/user/setHouse`, house,this.getHttpPostOptions());
    }

    createHouse(user: User,house: House){
        // return this.http.post(`http://localhost:8080/user/createHouse`, house,this.getHttpPostOptions());
        return this.http.post(environment.serverUrl + `/user/createHouse`, house,this.getHttpPostOptions());
    }

    refreshUser(user:User){
        const token = 'Bearer ' +localStorage.getItem("access_token"); 
        // return this.http.get<User>("http://localhost:8080/user/getUser",this.getHttpGetOptions());
        return this.http.get<User>(environment.serverUrl + "/user/getUser",this.getHttpGetOptions());
    }

    getHttpPostOptions() {
        // const token = localStorage.getItem('token');
        const token = 'Bearer ' +localStorage.getItem("access_token");
        const headers = {
            'Content-Type':  'application/json',
            'Authorization': token
          }
        return { withCredentials: true, headers: headers };
    }

    getHttpGetOptions() {
        // const token = localStorage.getItem('token');
        const token = 'Bearer ' +localStorage.getItem("access_token");
        const headers = {
            'Authorization': token
          }
        return { withCredentials: true, headers: headers };
    }


}