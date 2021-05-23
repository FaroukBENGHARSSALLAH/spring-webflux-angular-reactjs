import React, {Component} from 'react';


class Footer extends Component {
   
   
	render(){	
		  return  <footer  className="d-flex justify-content-center" >
                     <div className="copyright"  >
                        <div className="padding-nauth" >
				            <p>
					           <i><span>{ new Date().getFullYear() }</span> . <a  style={{ color: "crimson" }} href="mailto:farouk.bengarssallah@gmail.com" >Farouk BEN GHARSSALLAH</a></i> 
					     
					        </p>
					
			            </div> 
	                  </div> 		   
                   </footer>;    
				}
}

export default Footer;