import { useState } from "react";

const ReadComponent = ({id}) => {

    const [member, setMember] = useState([])

    return ( 
        <div>
            Board Read Component {id}
        </div>
     );
}
 
export default ReadComponent;