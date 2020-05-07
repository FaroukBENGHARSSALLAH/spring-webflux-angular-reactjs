import React, {Component}  from 'react';
import Navbar from './component/Navbar';
import Company from './component/Company';
import './App.css';

class App extends Component {
     render(){
			  return (<div  className="container"  >
					     <div  className="row" style={{ marginTop: "5%" }} >
						    <Navbar />
					        <Company />
					    </div>
					   </div>	
			        );
           }
}

export default App;