import React, {Component} from 'react';

class Transaction extends Component{
	
	 constructor(props) {
		super(props);
		this.state = {companies: [], scompany = {}, transactions = []};
	   }
	   
	   
	componentDidMount() {
		client(
		     {method: 'GET', 
			 path: '/api/companies'
			 })
	    .done(response => {
			this.setState({companies: response.companies});
		});
	 }

	    
	      
	handleClick = (company) => {
		 this.setState({scompany: company);
	     client(
		     {method: 'GET', 
			 path: '/api/companies/' + company.id + '/transactions'
			 })
	    .done(response => {
			this.setState({transactions: response.transactions});
		});
	          }
	
	
	
	
	   render(){
		   return (
				   <h3>Companies List</h3>
				   <div class="row">
				     <div class="col-md-6"  >
	                     this.sate.companies.map(function(company, index){
	                      return <div key={company.id}>
	                                <h4>{company.symbol} : {company.name}</h4>
	                                <button class="btn btn-info"  onClick=(this.handleClick({company})) type="button" >
	   				   	                 check variations
	   				   	            </button>
	                             </div;
	                            })
	                 </div>
				     </div>
				       <div class="col-md-6"  >
				   	        <h4>Transactions List</h4>
				            <div></div>',
				   	  </div>
				     </div>  
		       )
	   }
}