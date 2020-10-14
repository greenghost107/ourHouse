import { Component, Input, OnInit } from '@angular/core';
import { GroceryList, House } from '@app/_models';
import { HouseService, UserService } from '@app/_services';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrls: ['./house.component.scss']
})
export class HouseComponent implements OnInit {
  @Input() user;
  house: House;
  displayCreate: boolean = false;
  displayJoin: boolean = false;
  groceryLists = [];
  selectedGorceryListId: number;
  finishedLoading = false;
  displayGroceryListName:boolean = false;
  
  constructor(private userService: UserService,private houseService: HouseService, private messageService: MessageService) { }

  ngOnInit(): void {
   
    if(this.user)
    {
      this.refreshHouse().subscribe((house)=>{
        this.house=house;
        this.refreshGroceryLists().subscribe((groceryLists)=>{
          this.groceryLists=groceryLists;
          this.finishedLoading = true;
          
      });
      },
      (error)=>{
        this.messageService.add({severity:'error', summary:'Service Message', detail:'Via MessageService'});
        this.finishedLoading = true;

      });
    }
  }

  refreshHouse()
  {
      
      return this.houseService.refreshHouse(this.user);
  }

  showDialogCreateHouse() {
    this.displayCreate = true;
}
showDialogJoinHouse() {
    this.displayJoin = true;
}
refreshGroceryLists()
{
    return this.houseService.getGroceryListsForHouseId(this.house.id);
}

onsave(houseName,housePassword)
    {
        console.dir(houseName.value);
        // this.userService.joinUserToHouse(this.currentUser,new House(houseName.value,housePassword.value)).subscribe(()=>{});
        this.userService.createHouse(this.user,new House(houseName.value,housePassword.value)).subscribe(()=>{
            this.displayCreate = false;
            this.ngOnInit();
        });
        
        
    }

    onjoin(houseName,housePassword)
    {
        console.dir(houseName.value);
        this.userService.joinUserToHouse(this.user,new House(houseName.value,housePassword.value)).subscribe(()=>{
            this.displayJoin = false;
            this.ngOnInit();
        });
        
    }

    displayGroceryListNameDialog(){
      this.displayGroceryListName = true;
    }
    createNewGroceryList(groceryListName)
    {
      
      let groceryList :GroceryList = new GroceryList("",null,groceryListName.value);
      // groceryList.groceryListName = groceryListName.value;
        this.houseService.createNewGroceryList(this.house,groceryList).subscribe((grceryList)=>{
          this.groceryLists.push(grceryList);
          this.displayGroceryListName = false;
        });
    }


    getGroceryList(id:number)
    {
        
        this.selectedGorceryListId = id;
    }

}
