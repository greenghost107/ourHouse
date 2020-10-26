import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '@/_models';
import { House } from '@/_models';
import { GroceryList} from '@/_models';
import { Grocery} from '@/_models';
import {environment} from './../../environments/environment';
@Injectable({ providedIn: 'root' })
export class GroceryListService {
    constructor(private http: HttpClient) { }


    getGrocerysForId(id:number)
    {
        // return this.http.get<Grocery[]>("http://localhost:8080/groceryList/" + id,this.getHttpGetOptions());
        return this.http.get<Grocery[]>(environment.serverUrl + '/groceryList/' + id,this.getHttpGetOptions());
    }

    saveGroceryList(groceryList: Grocery[],id:number)
    {
        // return this.http.post<Grocery[]>("http://localhost:8080/groceryList/saveGroceryList/" + id,groceryList,this.getHttpPostOptions());
        return this.http.post<Grocery[]>(environment.serverUrl + '/groceryList/saveGroceryList/' + id,groceryList,this.getHttpPostOptions());
    }

    updateGroceryList(groceryList: Grocery[],id:number)
    {
        // return this.http.post<Grocery[]>("http://localhost:8080/groceryList/markGroceries/" + id,groceryList,this.getHttpPostOptions());
        return this.http.post<Grocery[]>(environment.serverUrl + '/groceryList/markGroceries/' + id,groceryList,this.getHttpPostOptions());
    }

    deleteGroceryList(id:number)
    {
        // return this.http.delete("http://localhost:8080/groceryList/deleteGroceryList/" + id,this.getHttpGetOptions());
        return this.http.delete(environment.serverUrl + '/groceryList/deleteGroceryList/' + id,this.getHttpGetOptions());
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
    // getHouse() {
    //     return this.http.get<House[]>(`http://localhost:8080/house`);
    // }

    // register(user: User) {
    //     return this.http.post(`http://localhost:8080/house`, user);
    // }

    // delete(id: number) {
    //     return this.http.delete(`http://localhost:8080/house/${id}`);
    // }

//     getGroceryListsForHouseId(houseId:number){
//         // const token = 'Bearer ' +localStorage.getItem("access_token");
//         return this.http.get<[GroceryList]>("http://localhost:8080/house/getGroceryLists/" + houseId,this.getHttpGetOptions());
//     }

//     createNewGroceryList(house:House)
//     {
//         // console.log(houseId);
//         // console.log('http://localhost:8080/house/createNewGroceryList/${houseId}');
//         return this.http.post<[GroceryList]>("http://localhost:8080/house/createNewGroceryList" ,{house},this.getHttpPostOptions());
//     }

//     refreshHouse(user:User){
//         const token = 'Bearer ' +localStorage.getItem("access_token");
//         return this.http.get<House>("http://localhost:8080/house/getHouse",this.getHttpGetOptions());
//     }

// getHttpPostOptions() {
//         // const token = localStorage.getItem('token');
//         const token = 'Bearer ' + localStorage.getItem("access_token");
//         const headers = {
//             'Content-Type':  'application/json',
//             'Authorization': token
//           }
//         return { withCredentials: true, headers: headers };
//     }

//     getHttpGetOptions() {
//         // const token = localStorage.getItem('token');
//         const token = 'Bearer ' +localStorage.getItem("access_token");
//         const headers = {
//             'Authorization': token
//           }
//         return { withCredentials: true, headers: headers };
//     }
}