export class GroceryList {
    id: number;
    creatorName: string;
    dt_created: Date;
    
constructor(creatorName:string,dt_created: Date)
{
    this.creatorName = creatorName;
    this.dt_created = dt_created;
}
}

