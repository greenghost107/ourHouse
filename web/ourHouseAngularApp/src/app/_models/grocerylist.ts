export class GroceryList {
    id: number;
    creatorName: string;
    dt_created: Date;
    groceryListName: string;
    
constructor(creatorName:string,dt_created: Date,groceryListName?:string,id?:number)
{
    this.creatorName = creatorName;
    this.dt_created = dt_created;
    if(id)
    {
        this.id = id;
    }
    if(groceryListName)
    {
        this.groceryListName = groceryListName;
    }
}
}

