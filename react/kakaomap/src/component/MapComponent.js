import { Map, MapMarker } from "react-kakao-maps-sdk";

const MapComponent = () => {
    
    return (
        <Map 
          center={{ lat: 37.5665, lng: 126.9780 }}   // 지도의 중심 좌표
          style={{ width: '800px', height: '600px' }} // 지도 크기
          level={3}                                   // 지도 확대 레벨
        >
            <MapMarker 
            position={{ lat: 37.5665, lng: 126.9780 }} // 지정된 위치 마커 추가
            >
            </MapMarker> 
        </Map>);
}
 
export default MapComponent;