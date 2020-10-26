import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '@/_models';
import { House } from '@/_models';
import { GroceryList} from '@/_models';
import {environment} from './../../environments/environment';
@Injectable({ providedIn: 'root' })
export class HouseService {
    constructor(private http: HttpClient) { }

    // getHouse() {
    //     return this.http.get<House[]>(`http://localhost:8080/house`);
    // }

    // register(user: User) {
    //     return this.http.post(`http://localhost:8080/house`, user);
    // }

    // delete(id: number) {
    //     return this.http.delete(`http://localhost:8080/house/${id}`);
    // }

    getGroceryListsForHouseId(houseId:number){
        // const token = 'Bearer ' +localStorage.getItem("access_token");
        // return this.http.get<[GroceryList]>("http://localhost:8080/house/getGroceryLists/" + houseId,this.getHttpGetOptions());
        return this.http.get<[GroceryList]>(environment.serverUrl + '/house/getGroceryLists/' + houseId,this.getHttpGetOptions());
    }

    createNewGroceryList(house:House,groceryList:GroceryList)
    {
        
        // console.log(houseId);
        // console.log('http://localhost:8080/house/createNewGroceryList/${houseId}');
        // return this.http.post<GroceryList>("http://localhost:8080/house/createNewGroceryList/" + house.id,groceryList,this.getHttpPostOptions());
        return this.http.post<GroceryList>(environment.serverUrl + '/house/createNewGroceryList/' + house.id,groceryList,this.getHttpPostOptions());
    }

    refreshHouse(user:User){
        const token = 'Bearer ' +localStorage.getItem("access_token");
        // return this.http.get<House>("http://localhost:8080/house/getHouse",this.getHttpGetOptions());
        return this.http.get<House>(environment.serverUrl + '/house/getHouse',this.getHttpGetOptions());
    }

getHttpPostOptions() {
        // const token = localStorage.getItem('token');
        const token = 'Bearer ' + localStorage.getItem("access_token");
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