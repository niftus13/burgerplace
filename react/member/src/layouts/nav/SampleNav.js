import { Link } from "react-router-dom";

const SampleNav = () => {
  
    return ( 
      <div className='flex m-6 p-4 font-extrabold'>
        
        <div className='m-4 text-4xl border-2'>
          <Link to="/">Main</Link>
          <span className='bg-red-500 font-extrabold'></span>
        </div>
        <div  className='m-4 text-4xl border-2'>
          <Link to="/admin/members">Admin</Link>
        </div>
      </div>
    );
  }
 
export default SampleNav;