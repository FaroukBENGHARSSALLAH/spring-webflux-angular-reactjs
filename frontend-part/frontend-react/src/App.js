import React, {Component}  from 'react';
import Navbar from './component/Navbar';
import Company from './component/Company';
import './App.css';

class App extends Component {
     render(){
			  return (<div className="App"  >
						         <Navbar />
					             <Company />
					 </div>
			        );
           }
}

export default App;