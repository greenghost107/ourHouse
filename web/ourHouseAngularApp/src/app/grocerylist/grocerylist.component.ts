import { Component, Input, OnInit } from '@angular/core';
import { Grocery } from '@app/_models';
import { GroceryListService } from '@app/_services';

@Component({
  selector: 'app-grocerylist',
  templateUrl: './grocerylist.component.html',
  styleUrls: ['./grocerylist.component.scss']
})
export class GrocerylistComponent implements OnInit {
  @Input() id;
  groceryList: Grocery[];
  toSave: Grocery[] = [];
  finishedLoading:boolean = false;

  constructor(private groceryListService: GroceryListService) { }

  ngOnInit(): void {
    if (this.id) {
      this.groceryListService.getGrocerysForId(this.id).subscribe((data)=>{
            
            this.groceryList = data;
            this.finishedLoading = true;
        });
    }
  }

  addGrocery(groceryNameInput,quantityInput){
    this.groceryList.push({name: groceryNameInput.value, quantity: Number(quantityInput.value)});
    this.toSave.push({name: groceryNameInput.value, quantity: Number(quantityInput.value)});
    
  }

  saveGroceryList()
  {
    this.groceryListService.saveGroceryList(this.toSave,this.id).subscribe(()=>{});
    console.log("finished saving");
  }

}
