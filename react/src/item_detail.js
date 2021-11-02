import {Link} from "react-router-dom";
import React, {Component} from 'react';
import './item_detail.css';
import { handleAPIError } from "./errors";


class item_detail extends Component{
    constructor(props) {
        super();
        this.state ={
            item: null,
            relatedItems: null,
        }
    }
    componentDidMount() {
        const id = this.props.match.params.id;
        const requestOptions = {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }

        };

        fetch(`/api/getItem/${id}`, requestOptions)
            .then(res=>{
                if (res.status !== 200) {
                    handleAPIError(res);
                } else {
                    res.json().then(data => {
                        const item = data.item;
                        this.setState({
                            item: item
                        })
                    })
                }
            })

        fetch('/api/allitem',requestOptions)
            .then(res=>{
                if (res.status !== 200) {
                    handleAPIError(res);
                } else {
                    res.json().then(data => { 
                        const items = data.item;
                        const otherItems = items.filter(item => item.itemId !== id).slice(0,5);
                        this.setState({
                            relatedItems: otherItems
                        }) 
                    });
                }
            });
    }

    render() {
        let{item,relatedItems} = this.state;

        const related = relatedItems ? relatedItems.map(item=>(
            <div className="itemImg">
                <Link to={'/item/'+item.itemId.toString()}>
                    <img src={item.images[0]} alt={item.name} />
                </Link>
                <p>{item.name}</p>
                <p>${item.price}</p>
            </div>
        )) : null;

        return (
            <div className="home">
                <div className="header">
                    <Link to="/">
                        <p className="title">UB Marketplace</p>
                    </Link>
                    <div className="dropdown">
                        <button className="dropbtn"></button>
                        <div className="dropdown-content">
                            <Link to="/login">Login/Register</Link>
                            <Link to="/profile">View Profile</Link>
                            <Link to="/">Logout</Link>
                        </div>
                    </div>
                </div>
                {item ?
                    <div className="itemInfo">
                        <div className="itemLeft">
                            <div className="itemThumbnail">
                                <img src={item.images[0]} alt={item.name} />
                            </div>
                        </div>
                        <div className="itemRight">
                            <div className="itemName">
                                <h2>{item.name}</h2>
                            </div>
                            <div className="itemPrice">
                                <h3>Price: ${item.price}</h3>
                            </div>
                            <div className="itemLocation">
                                <h3>Available Meetup Location</h3>
                                <button>{item.meetingPlace}</button>
                            </div>
                            <h3 className="descriptionTitle">Item Description</h3>
                            <div className="itemDescription">
                                {item.description}
                            </div>
                        </div>
                    </div>
                : 
                    <p>Loading...</p>
                }
                { related ? 
                    <div className="relatedList">
                        <div className="relatedItems">
                            <h3>Related Items</h3>
                        </div>
                        {related}
                    </div>
                : '' }
            </div>
        );
    }
}

export default item_detail;
