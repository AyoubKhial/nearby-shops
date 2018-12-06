export class Shop {
    public _id: string;
    public picture: string;
    public name: string;
    public email: string;
    public city: string;
    public location: {
        type: string,
        coordinates: Array<number>[2]
    }
}