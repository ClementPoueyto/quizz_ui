import { Grommet, Grid, Box, Text } from 'grommet'
function App() {
	return (
		<Grommet>
			<Grid
				rows={['medium','medium',]}
				columns={['small','large',]}
				gap='null'
				areas={[
					{ name: 'header', start: [0, 0], end: [1, 0] },
					{ name: 'left', start: [0, 0], end: [0, 1] },
				]}
			>
				<Box gridArea='header' background='blue' >
					<Text size='large'  textAlign='center'  >simple text</Text>
					<Text size='medium'  textAlign='center'  >simple text</Text>
				</Box>
				<Box gridArea='left' background='brand' >
				</Box>
			</Grid>
		</Grommet>
	);
}
export default App;