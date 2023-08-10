import { useState } from "react";


const initState = {
    id:'',
    pw:'',
    nickname:'',
    admin:0
  }

const ReadComponent = ({id}) => {

    const [member, setMember] = useState(initState)

    return ( 
        <div>
            Board Read Component {id}
        </div>
     );
}
 
export default ReadComponent;