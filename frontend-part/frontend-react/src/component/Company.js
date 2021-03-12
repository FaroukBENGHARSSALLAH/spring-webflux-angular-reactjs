import React, {Component} from 'react';
import axios from 'axios';
import Transaction from './Transaction';


class Company extends Component {
	   
   constructor() {
            super();
			this.state = {
					symbol : '',
					company : null,
					content : '',
					companies : []
				  };
       }
	   
	componentDidMount(){
            axios.get('http://localhost:8080/api/companies/').then(res => {
			   this.setState({
			         companies : res.data
				 });
				  const innerv = (<div>
						<div className="table-reponsive">
							<table className="table table-borderless">
							  <thead>
							     <tr>
								 <th>Symbol</th>
								 <th>Name</th>
								 <th>Exchange</th>
								 <th>Action</th>
								 </tr>
							  </thead>
							  <tbody>
							     {this.state.companies.map((item,i) => { return (
										<tr key={item.symbol} >
										<td>{item.symbol}</td>
										<td>{item.name}</td>
										<td>{item.exchange}</td>
										<td>
											<button type="button"  className="btn btn-outline-info btn-sm"  onClick={() => this.fetch(item.symbol)} >
												 <i className="fa fa-check" ></i>
											</button>
										</td>
									  </tr>
									);})}
							  </tbody>
							</table>
						</div>
						</div>);						
				  this.setState({content: innerv});
			 });
       }	
	   
	

     back(){
		  this.setState({
			    symbol : '', 
				company : null
				});
		 const innerv =  (<div>
						<div className="table-reponsive">
							<table className="table table-borderless">
							  <thead>
							     <tr>
								 <th>Symbol</th>
								 <th>Name</th>
								 <th>Exchange</th>
								 <th>Action</th>
								 </tr>
							  </thead>
							  <tbody>
                                   {this.state.companies.map((item,i) => { return (
										<tr key={item.symbol} >
										<td>{item.symbol}</td>
										<td>{item.name}</td>
										<td>{item.exchange}</td>
										<td>
											<button type="button"  className="btn btn-outline-info btn-sm"  onClick={() => this.fetch(item.symbol)} >
												 <i className="fa fa-check" ></i>
											</button>
										</td>
									  </tr>
									);})}
							  </tbody>
							</table>
						</div>
						</div>);						
				  this.setState({content: innerv});
       }	   
	   
	   
	   
	  fetch(symbol){
		  this.setState({symbol : symbol}); 
		  axios.get('http://localhost:8080/api/companies/' + symbol).then(res => {
			   this.setState({
				   company : res.data
				 });
		       const infovl = <div>
								   <div  className="float-right"  style={{ marginLeft: "10px" }}    > 
								      <button type="button"  className="btn btn-outline-info btn-sm" style={{ marginRight: "15px" }}     onClick={() => this.back()} >
										 <i className="fa fa-step-backward" ></i>
									 </button>
								   </div>
								   <div className="row" >
										   <div  className="col-md-4 col-lg-4"  > 
										   <div className="card" >
											  <div className="card-body">
												<h5 className="card-title float-left"  style={{ marginRight: "6px" }}  >{this.state.company.symbol}</h5>
												<div className="float-left" style={{ marginLeft: "5px" }} >
													<p className="card-text">Name : {this.state.company.name}</p>
													<p className="card-text">Exchange : {this.state.company.exchange}</p>
													<p className="card-text">Volume : {this.state.company.volume}  $</p>
													<p className="card-text">1 Day Flow : <span   className={(this.state.company.day1Flow > 0)  ?  "grennvl" : "redvl"}  >{this.state.company.day1Flow} </span> $</p>
												</div>
											  </div>
											 </div>
											</div>
											<div  className="col-md-8 col-lg-8"  >
											 <div  className="card"  >
												<div  className="card-body"  >
													<Transaction price={this.state.company.day1Flow}  symbol={this.state.company.symbol} />
												 </div>
											  </div>
											</div>
									</div>		

							   </div>;
			   this.setState({content: infovl});
		}); 
       }
	   
	   
	   
	    ignore = (e) => {
			e.preventDefault();
		  }
	 
	render(){
		  
		   
		  return (
		        <div>
				
					 <br/><br/><br/><br/>
					 <div  className="container-fluid"  >
					     <div  className="row"  >
						       <div  className="col-md-11 col-lg-11"  >
									 <div  className="card"  >
									    <div  className="card-body"  >
									        {this.state.content}
									     </div>
									 </div>
							    </div>
							    <div  className="col-md-1 col-lg-1"  >  </div>
					    </div>
					  </div>
				 </div>
		        );
			}
    }

export default Company;