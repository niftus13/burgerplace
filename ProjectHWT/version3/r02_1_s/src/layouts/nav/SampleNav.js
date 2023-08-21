import { Link } from "react-router-dom";
import LoginNav from "./LoginNav";
import styles from "./navCss/smapleNav.module.css";

const SampleNav = () => {


    return (


        <div>
            <div className={styles.con2}>
                <div>
                    <Link className={styles.link1} to="/">Main</Link>
                </div>
                <div >
                    <Link className={styles.link2} to="/about">About</Link>
                </div>
                <div>
                    <Link className={styles.link3} to="/tboard/list">TradeBoard</Link>
                </div>
                <div>
                    <Link className={styles.link4} to="/board/list">FreeBoard</Link>
                </div>
                <div>
                    <LoginNav className={styles.link5} ></LoginNav>
                </div>
            </div>


        </div>
    );
}

export default SampleNav;