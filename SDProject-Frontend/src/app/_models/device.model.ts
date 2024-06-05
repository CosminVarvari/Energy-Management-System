import { UserId } from "./userid.model";

export class Device {
    deviceId!: string;
    description!: string;
    address!: string;
    maxhourec!: string;
    userID!: UserId;
}