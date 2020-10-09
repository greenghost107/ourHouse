import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { GroceryList, User } from '@/_models';
import { House } from '@/_models';
import { HouseService,UserService, AuthenticationService } from '@/_services';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit {
    currentUser: User;
    users = [];
    house: House;
    displayCreate: boolean = false;
    displayJoin: boolean = false;
    groceryLists = [];

    constructor(
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private houseService: HouseService
    ) {
        this.currentUser = this.authenticationService.currentUserValue;
        // this.refreshUser().subscribe((user)=>this.currentUser.houseId=user.houseId);
        
    }

    ngOnInit() {
        // this.loadAllUsers();
        //TODO
        // this.loadHouseForUser();
        // console.log(this.currentUser);
        // this.loadHouse
        // this.refreshUser().subscribe((user)=>{
            // this.house=house;
            // this.currentUser = user;
        //     if(this.house)
        // {
        //     // getGroceryList and put it in a list
        //     this.groceryLists = ["123","456"];
        // }
        // });

        


        // this.refreshHouse().subscribe((house)=>{
            
        //     this.house = house;
        
        // });

        this.refreshHouse().subscribe((house)=>{
            this.house=house;
            this.refreshGroceryLists().subscribe((groceryLists)=>{
                console.log(groceryLists);
                this.groceryLists=groceryLists;
            });
        });
        


        // this.refreshUser().subscribe((user)=>{
        //     this.currentUser.houseId=user.houseId;
        // });
        
        
        
    }
    showDialogCreateHouse() {
        this.displayCreate = true;
    }
    showDialogJoinHouse() {
        this.displayJoin = true;
    }

    onsave(houseName,housePassword)
    {
        console.dir(houseName.value);
        // this.userService.joinUserToHouse(this.currentUser,new House(houseName.value,housePassword.value)).subscribe(()=>{});
        this.userService.createHouse(this.currentUser,new House(houseName.value,housePassword.value)).subscribe(()=>{
            this.displayCreate = false;
            this.ngOnInit();
        });
        
        
    }

    onjoin(houseName,housePassword)
    {
        console.dir(houseName.value);
        this.userService.joinUserToHouse(this.currentUser,new House(houseName.value,housePassword.value)).subscribe(()=>{
            this.displayJoin = false;
            this.ngOnInit();
        });
        
    }
    
    createNewGroceryList()
    {
        // console.log(this.currentUser.houseId);
        // console.log(this.house.id);
        this.houseService.createNewGroceryList(this.house).subscribe(()=>{});
    }

    refreshUser()
    {
        
        return this.userService.refreshUser(this.currentUser);
    }

    refreshHouse()
    {
        
        return this.houseService.refreshHouse(this.currentUser);
    }

    refreshGroceryLists()
    {
        // console.log(this.house.id);
        return this.houseService.getGroceryListsForHouseId(this.house.id);
    }
    // deleteUser(id: number) {
    //     this.userService.delete(id)
    //         .pipe(first())
    //         .subscribe(() => this.loadAllUsers());
    // }

    // private loadAllUsers() {
    //     this.userService.getAll()
    //         .pipe(first())
    //         .subscribe(users => this.users = users);
    // }

    // private joinUserToHouse(house: House){
    //     console.log("Home Component join user to house")
    //     this.userService.joinUserToHouse(this.currentUser,house)
    //     .pipe(first())
    //     .subscribe(house=>this.house=house);
    // }

   
}

