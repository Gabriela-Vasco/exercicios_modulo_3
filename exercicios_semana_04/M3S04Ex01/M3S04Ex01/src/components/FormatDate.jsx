export default function FormatDate({ seconds }) {
    const convertTime = (seconds) => {
      const hours = Math.floor(seconds / 3600);
      const minutes = Math.floor((seconds % 3600) / 60);
      const secondsDate = seconds % 60;
  
      return `${hours.toString().padStart(2, "0")}:${minutes
        .toString()
        .padStart(2, "0")}:${secondsDate.toString().padStart(2, "0")}`;
    };
  
    return (
      <div>
        <p>{convertTime(seconds)}</p>
      </div>
    );
  }
  