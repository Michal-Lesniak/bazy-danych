import { Appliance } from "./appliance";
import { Customer } from "./customer";
import { DateAction } from "./dateAction";
import { Part } from "./part";

export interface RepairDetails {
    repairCode:string,
    status:boolean,
    customer: Customer,
    appliance: Appliance,
    parts: Part[],
    dateActionDtoList: DateAction[]
}
