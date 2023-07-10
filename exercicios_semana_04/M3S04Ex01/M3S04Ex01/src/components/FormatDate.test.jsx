import { render, screen } from '@testing-library/react';
import FormatDate from './FormatDate';

describe('Conversion of seconds to time', () => {
    test('Conversion of 30 seconds to time', () => {
      const { seconds, expected } = { seconds: 30, expected: '00:00:30' };

      render(<FormatDate seconds={seconds} />);

      const resultElement = screen.getByText(expected);
      expect(resultElement).toBeInTheDocument();
    });
  
    test('Conversion of 350 seconds to time', () => {
      const { seconds, expected } = { seconds: 350, expected: '00:05:50' };

      render(<FormatDate seconds={seconds} />);

      const resultElement = screen.getByText(expected);
      expect(resultElement).toBeInTheDocument();
    });
  
    test('Conversion of 3725 seconds to time', () => {
      const { seconds, expected } = { seconds: 3725, expected: '01:02:05' };

      render(<FormatDate seconds={seconds} />);
      
      const resultElement = screen.getByText(expected);
      expect(resultElement).toBeInTheDocument();
    });
  });

